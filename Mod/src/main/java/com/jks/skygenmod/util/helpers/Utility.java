package com.jks.skygenmod.util.helpers;

public final class Utility {
	public static boolean isPositionWithinChunk(int posX, int posZ, int chunkX, int chunkZ) {
		int chunkPosX = chunkX * 16;
		int chunkPosZ = chunkZ * 16;
		
		return (posX >= chunkPosX
				&& posX < chunkPosX + 16
				&& posZ >= chunkPosZ
				&& posZ < chunkPosZ + 16);
	}
}
