(defn digit-power-sum
  "Return the sum of the digits of n raised to exponent p.

  Accepts any integer n, including negative values. The sign is ignored.
  Uses BigInt arithmetic so large powers are supported.

  Examples:
  (digit-power-sum 2 3)    ;; 8, because 2^3 = 8
  (digit-power-sum 12 2)   ;; 9, because 12^2 = 144 and 1+4+4 = 9
  (digit-power-sum -5 3)   ;; 8, because |-5|^3 = 125 and 1+2+5 = 8"
  [n p]
  (->> (str (bigint (Math/pow (Math/abs (double n)) p)))
       (map #(Character/digit ^char % 10))
       (reduce +)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 8 (digit-power-sum 2 3)))
  (is (= 9 (digit-power-sum 12 2)))
  (is (= 8 (digit-power-sum -5 3))))

(run-test test-variation)
