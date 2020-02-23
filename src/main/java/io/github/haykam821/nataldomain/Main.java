package io.github.haykam821.nataldomain;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensionType;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;

public class Main implements ModInitializer {
	public static Biome BIRTHFIELD;
	public static FabricDimensionType NATAL_DOMAIN;

	@Override
	public void onInitialize() {
		BIRTHFIELD = Registry.register(Registry.BIOME, new Identifier("nataldomain", "birthfield"), new BirthfieldBiome());
		NATAL_DOMAIN = FabricDimensionType
			.builder()
			.defaultPlacer((oldEntity, destinationWorld, portalDir, horizontalOffset, verticalOffset) -> {
				return new BlockPattern.TeleportTarget(
					new Vec3d(destinationWorld.getTopPosition(Heightmap.Type.WORLD_SURFACE, BlockPos.ORIGIN)).multiply(0.1),
					oldEntity.getVelocity(),
					(int) oldEntity.yaw
				);
			})
			.factory(NatalDomain::new)
			.buildAndRegister(new Identifier("nataldomain", "natal_domain"));
	}
}