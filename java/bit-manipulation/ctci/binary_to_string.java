/*
Binary to String: Given a real number between 0 and 1 (e.g., 0.72) that is passed in as a double, print the binary representation.Ifthe number cannot be represented accurately in binary with at most 32 characters, print "ERROR:'
*/
String printBinary(double num){
	if (num > 1 || num < 0) return "ERROR";

	StringBuilder builder = new StringBuilder();
	builder.append(".");
	while (num > 0) {
		if (builder.length() > 32) return "ERROR";

		int r = num*2;
		if (r > 1) {
			builder.append(1);
			num = r - 1;
		} else {
			builder.append(0);
			num = r;
		}
	}
	return builder.toString();
}
