package com.cn.cae.util;

/**
 * <h3>音量计算辅助类</h3>
 * <p>
 * 		用于辅助计算pcm音频音量等。
 * </p>
 * 
 * @author iFlytek &nbsp;&nbsp;&nbsp;<a href="http://www.xfyun.cn/">讯飞语音云</a>
 * @since version:1073
 * 
 */
public class VolumeUtil {
	// 音量级别与能量值对应关系
	private final static int VOLUME_LEVEL_0 = 329;
	private final static int VOLUME_LEVEL_1 = 421;
	private final static int VOLUME_LEVEL_2 = 543;
	private final static int VOLUME_LEVEL_3 = 694;
	private final static int VOLUME_LEVEL_4 = 895;
	private final static int VOLUME_LEVEL_5 = 1146;
	private final static int VOLUME_LEVEL_6 = 1476;
	private final static int VOLUME_LEVEL_7 = 1890;
	private final static int VOLUME_LEVEL_8 = 2433;
	private final static int VOLUME_LEVEL_9 = 3118;
	private final static int VOLUME_LEVEL_10 = 4011;
	private final static int VOLUME_LEVEL_11 = 5142;
	private final static int VOLUME_LEVEL_12 = 6612;
	private final static int VOLUME_LEVEL_13 = 8478;
	private final static int VOLUME_LEVEL_14 = 10900;
	private final static int VOLUME_LEVEL_15 = 13982;
	private final static int VOLUME_LEVEL_16 = 17968;
	private final static int VOLUME_LEVEL_17 = 23054;
	private final static int VOLUME_LEVEL_18 = 29620;
	private final static int VOLUME_LEVEL_19 = 38014;
	private final static int VOLUME_LEVEL_20 = 48828;
	private final static int VOLUME_LEVEL_21 = 62654;
	private final static int VOLUME_LEVEL_22 = 80491;
	private final static int VOLUME_LEVEL_23 = 103294;
	private final static int VOLUME_LEVEL_24 = 132686;
	private final static int VOLUME_LEVEL_25 = 170366;
	private final static int VOLUME_LEVEL_26 = 218728;
	private final static int VOLUME_LEVEL_27 = 280830;
	
	/**
	 * <h4>计算pcm音频音量</h4>
	 * 
	 * @param pcmFrame 音频数据帧，每个采样值长16bit。
	 * @param length 数据长度，帧的字节数。
	 * @return 音量值[0-30]。
	 */
	public static int computeVolume(byte[] pcmFrame, int length) {
		if (null == pcmFrame || length <= 2) {
			return 0;
		}
		
		// 采样长度，即有多少个16bit采样值
		int sampleLen = length / 2;
		// 采样平均值
		long meanVal = 0;
		
		for (int i = 0; i < 2 * sampleLen - 1; i += 2) {
			int sampleVal = pcmFrame[i] + pcmFrame[i + 1] * 256;
			meanVal += sampleVal;
		}
		meanVal /= sampleLen;
		
		// 帧能量值
		long frameEnergy = 0;
		for (int i = 0; i < 2 * sampleLen - 1; i += 2) {
			int sampleVal = pcmFrame[i] + pcmFrame[i + 1] * 256;
			int temp = (int) (sampleVal - meanVal);
			frameEnergy += ((temp * temp) >> 9);
		}
		frameEnergy /= sampleLen;
		
		if (frameEnergy < VOLUME_LEVEL_0) {
			return 0;
		} else if (frameEnergy < VOLUME_LEVEL_1) {
			return 1;
		} else if (frameEnergy < VOLUME_LEVEL_2) {
			return 2;
		} else if (frameEnergy < VOLUME_LEVEL_3) {
			return 3;
		} else if (frameEnergy < VOLUME_LEVEL_4) {
			return 4;
		} else if (frameEnergy < VOLUME_LEVEL_5) {
			return 5;
		} else if (frameEnergy < VOLUME_LEVEL_6) {
			return 6;
		} else if (frameEnergy < VOLUME_LEVEL_7) {
			return 7;
		} else if (frameEnergy < VOLUME_LEVEL_8) {
			return 8;
		} else if (frameEnergy < VOLUME_LEVEL_9) {
			return 9;
		} else if (frameEnergy < VOLUME_LEVEL_10) {
			return 10;
		} else if (frameEnergy < VOLUME_LEVEL_11) {
			return 11;
		} else if (frameEnergy < VOLUME_LEVEL_12) {
			return 12;
		} else if (frameEnergy < VOLUME_LEVEL_13) {
			return 13;
		} else if (frameEnergy < VOLUME_LEVEL_14) {
			return 14;
		} else if (frameEnergy < VOLUME_LEVEL_15) {
			return 15;
		} else if (frameEnergy < VOLUME_LEVEL_16) {
			return 16;
		} else if (frameEnergy < VOLUME_LEVEL_17) {
			return 17;
		} else if (frameEnergy < VOLUME_LEVEL_18) {
			return 18;
		} else if (frameEnergy < VOLUME_LEVEL_19) {
			return 19;
		} else if (frameEnergy < VOLUME_LEVEL_20) {
			return 20;
		} else if (frameEnergy < VOLUME_LEVEL_21) {
			return 21;
		} else if (frameEnergy < VOLUME_LEVEL_22) {
			return 22;
		} else if (frameEnergy < VOLUME_LEVEL_23) {
			return 23;
		} else if (frameEnergy < VOLUME_LEVEL_24) {
			return 24;
		} else if (frameEnergy < VOLUME_LEVEL_25) {
			return 25;
		} else if (frameEnergy < VOLUME_LEVEL_26) {
			return 26;
		} else if (frameEnergy < VOLUME_LEVEL_27) {
			return 27;
		} else {
			return 30;
		}
	}
	
}
