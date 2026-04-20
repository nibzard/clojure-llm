(defn armstrong-number?
  "Return true if n is an Armstrong number, otherwise false.

  Works for non-negative integers. For example:
  (armstrong-number? 153) => true
  (armstrong-number? 10)  => false
  (armstrong-number? 0)   => true"
  [n]
  (let [digits (map #(Character/digit ^char % 10) (str n))
        p      (count digits)
        sum    (reduce + (map #(long (Math/pow % p)) digits))]
    (= sum n)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (armstrong-number? 153)))
  (is (= false (armstrong-number? 10)))
  (is (= true (armstrong-number? 9474))))

(run-test test-variation)
