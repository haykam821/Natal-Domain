package io.github.haykam821.nataldomain;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.source.BiomeSourceType;
import net.minecraft.world.biome.source.FixedBiomeSourceConfig;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.OverworldDimension;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorType;
import net.minecraft.world.gen.chunk.OverworldChunkGeneratorConfig;

public class NatalDomain extends OverworldDimension {
	private static final Vec3d FOG_COLOR = new Vec3d(0.5, 0.4, 0.9);

	public NatalDomain(World world, DimensionType type) {
        super(world, type);
    }

	@Override
	public ChunkGenerator<?> createChunkGenerator() {
 		OverworldChunkGeneratorConfig overworldConfig = ChunkGeneratorType.SURFACE.createSettings();
		overworldConfig.setDefaultBlock(Blocks.END_STONE.getDefaultState());
		overworldConfig.setDefaultFluid(Blocks.WATER.getDefaultState());

		FixedBiomeSourceConfig biomeConfig = BiomeSourceType.FIXED
			.getConfig(world.getLevelProperties())
			.setBiome(Main.BIRTHFIELD);

		return ChunkGeneratorType.SURFACE.create(world, BiomeSourceType.FIXED.applyConfig(biomeConfig), overworldConfig);
	}

	@Override
	public float getSkyAngle(long worldTime, float tickDelta) {
		return 0.05F;
	}

	@Environment(EnvType.CLIENT)
	@Override
	public Vec3d getFogColor(float skyAngle, float tickDelta) {
		return FOG_COLOR;
	}

	@Override
	public DimensionType getType() {
		return Main.NATAL_DOMAIN;
	}
}