package io.github.haykam821.nataldomain;

import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.placer.DoublePlantPlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public class BirthfieldBiome extends Biome {
	public static RandomPatchFeatureConfig NATAL_GRASS_CONFIG = new RandomPatchFeatureConfig.Builder(
			new SimpleBlockStateProvider(Blocks.TALL_GRASS.getDefaultState()),
		new DoublePlantPlacer()
	).tries(4).build();

	public BirthfieldBiome() {
		super(new Biome.Settings()
			.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.END_CONFIG)
			.precipitation(Biome.Precipitation.NONE)
			.category(Biome.Category.THEEND)
			.depth(0.06F)
			.scale(0.0F)
			.temperature(0.5F)
			.downfall(0.0F)
			.waterColor(4159204)
			.waterFogColor(329011)
			.parent((String) null)
		);

		this.addFeature(
			GenerationStep.Feature.VEGETAL_DECORATION,
			Feature.RANDOM_PATCH
				.configure(NATAL_GRASS_CONFIG)
				.createDecoratedFeature(
					Decorator.COUNT_HEIGHTMAP_DOUBLE
					.configure(new CountDecoratorConfig(25))
				)
		);
	}
}
