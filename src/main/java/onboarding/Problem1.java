package onboarding;

import java.util.List;

class Problem1 {
    final private static int POBI_WIN = 1;
    final private static int CRONG_WIN = 0;
    final private static int EXCEPTION = -1;
    static int playerCounter = 0;

    public static int solution(List<Integer> pobi, List<Integer> crong) {
        if (isException(pobi, crong)) return EXCEPTION;

        int answer = Integer.MAX_VALUE;
        return answer;
    }

    private static boolean isException(List<Integer> pobi, List<Integer> crong) {
        try {
            playerCounter = 0;
            new Validation(pobi).validate();
            new Validation(crong).validate();
        } catch (ValidationException e) {
            System.out.println(whoIsErrorPlayer() + e);
            return true;
        }

        return false;
    }

    private static String whoIsErrorPlayer() {
        return playerCounter + "번째 플레이어에게서 오류가 발생했습니다. (0부터 셉니다)\n";
    }

    private static class Validation {
        private List<Integer> player;

        Validation(List<Integer> player) {
            this.player = player;
        }

        public void validate() throws ValidationException {
            int left = player.get(0);
            int right = player.get(1);
            isLenEqualsTwo(player.size());
            isLeftOdd(left);
            isRightEven(right);
            isMoreThanMinimum(left);
            isLessThanMaximum(right);
            isNextPage(left, right);
            playerCounter++;
        }

        private void isLenEqualsTwo(Integer len) throws ValidationException {
            if (len != 2) throw new ValidationException("펼친 페이지는 총 2페이지여야 합니다.");
        }

        private void isLeftOdd(Integer left) throws ValidationException {
            if (left % 2 == 0) throw new ValidationException("왼쪽 페이지는 홀수여야 합니다.");
        }

        private void isRightEven(Integer right) throws ValidationException {
            if (right % 2 == 0) throw new ValidationException("오른쪽 페이지는 짝수여야 합니다.");
        }

        private void isMoreThanMinimum(Integer left) throws ValidationException {
            if (left >= 1) throw new ValidationException("1페이지보다 커야 합니다.");
        }

        private void isLessThanMaximum(Integer right) throws ValidationException {
            if (right <= 400) throw new ValidationException("400페이지보다 작아야 합니다.");
        }

        private void isNextPage(Integer left, Integer right) throws ValidationException {
            if (right - left != 1) throw new ValidationException("오른쪽 페이지는 항상 왼쪽 페이지의 다음 페이지여야 합니다.");
        }
    }
}

class ValidationException extends Exception {
    ValidationException(String message) {
        super(message);
    }
}