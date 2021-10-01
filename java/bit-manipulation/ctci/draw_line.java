/*
Draw Line: A monochrome screen is stored as a single array of bytes, allowing eight consecutive pixels to be stored in one byte. The screen has width w, where wis divisible by 8 (that is, no byte will be split across rows). The height of the screen, of course, can be derived from the length of the array and the width. Implement a function that draws a horizontal line from(xl, y)to(x2, y).
The method signature should look something like:
drawLine(byte[] screen, int width, int xl, int x2, int y)
*/
void drawLine(byte[] screen, int width, int x1, int x2, int y) {
	int startOffset = x1%8;
	int startByte = x1/8;
	if (startOffset != 0) startByte++;

	int endOffset = x2%8;
	int endByte = x2/8;
	if (endOffset != 7) endByte--;

	for (int b = startByte; b <= endByte; b++) {
		screen[(width/8)*y + b] = 0xFF;
	}

	byte startMask = (byte) 0xFF >> startOffset;
	byte endMask = (byte) ~(0xFF >> (endOffset + 1));

	if (x1/8 == x2/8) {
		byte mask = (byte) (startMask & endMask);
		screen[(width/8)*y + x1/8] |= mask;
	} else {
		if (startOffset != 0) {
			screen[(width/8)*y + (startByte-1)] |= startMask;
		}
		if (endOffset != 7) {
			screen[(width/8)*y + (endByte+1)] |= endMask;
		}
	}
}
