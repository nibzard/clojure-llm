(defn max-number-from-digits
  "Return the largest number that can be formed by concatenating the given digits.
  Accepts any seq of integers and ignores nil values.

  Examples:
  (max-number-from-digits [3 30 34 5 9]) ;=> 9534330
  (max-number-from-digits [0 0 1])       ;=> 100
  (max-number-from-digits nil)           ;=> 0"
  [digits]
  (let [ds (keep identity digits)
        s  (sort-by #(str %1 %2) #(compare %2 %1) ds)]
    (if (seq s)
      (Long/parseLong (apply str s))
      0)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 9534330 (max-number-from-digits [3 30 34 5 9]))))

(run-test test-variation)
