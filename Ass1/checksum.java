public class checksum {

    // Function to find the One's complement of the given binary string
    public static String onesComplement(String data) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == '0')
                result.append('1');
            else
                result.append('0');
        }
        return result.toString();
    }

    // Function to return the checksum value of the given string when divided in K size blocks
    public static String checkSum(String data, int blockSize) {
        // Check data size is divisible by blockSize
        // Otherwise add '0' front of the data
        int n = data.length();
        if (n % blockSize != 0) {
            int padSize = blockSize - (n % blockSize);
            for (int i = 0; i < padSize; i++) {
                data = '0' + data;
            }
        }

        // Binary addition of all blocks with carry
        String result = "";

        // First block of data stored in result variable
        for (int i = 0; i < blockSize; i++) {
            result += data.charAt(i);
        }

        // Loop to calculate the block wise addition of data
        for (int i = blockSize; i < n; i += blockSize) {
            // Stores the data of the next block
            String nextBlock = "";
            for (int j = i; j < i + blockSize; j++) {
                nextBlock += data.charAt(j);
            }

            // Stores the binary addition of two blocks
            StringBuilder additions = new StringBuilder();
            int sum = 0, carry = 0;

            // Loop to calculate the binary addition of the current two blocks of k size
            for (int k = blockSize - 1; k >= 0; k--) {
                sum += (nextBlock.charAt(k) - '0') + (result.charAt(k) - '0');
                carry = sum / 2;
                if (sum == 0) {
                    additions.insert(0, '0');
                    sum = carry;
                } else if (sum == 1) {
                    additions.insert(0, '1');
                    sum = carry;
                } else if (sum == 2) {
                    additions.insert(0, '0');
                    sum = carry;
                } else {
                    additions.insert(0, '1');
                    sum = carry;
                }
            }

            // After binary add of two blocks with carry, if carry is 1 then apply binary addition
            StringBuilder finalResult = new StringBuilder();

            if (carry == 1) {
                for (int l = additions.length() - 1; l >= 0; l--) {
                    if (carry == 0) {
                        finalResult.insert(0, additions.charAt(l));
                    } else if (((additions.charAt(l) - '0') + carry) % 2 == 0) {
                        finalResult.insert(0, '0');
                        carry = 1;
                    } else {
                        finalResult.insert(0, '1');
                        carry = 0;
                    }
                }
                result = finalResult.toString();
            } else {
                result = additions.toString();
            }
        }

        // Return One's complements of result value which represents the required checksum value
        return onesComplement(result);
    }

    // Function to check if the received message is same as the senders message
    public static boolean checker(String Message, String checksum, int blockSize) {
        // Checksum Value of the senders message

        // Checksum value for the receivers message
        String receiverChecksum = checkSum(Message + checksum, blockSize);

        // If receivers checksum value is 0
        return receiverChecksum.chars().filter(ch -> ch == '0').count() == blockSize;
    }

    // Driver Code
    public static void main(String[] args) {
    
    }
}

