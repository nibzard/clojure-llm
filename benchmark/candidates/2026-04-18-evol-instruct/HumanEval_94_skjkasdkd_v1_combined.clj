(defn largest-prime-digit-sum
  "You are given a collection of integers, which may be lazy and may contain nil values.
  Return the sum of the digits of the largest prime number in the collection.
  Ignore nil values and non-positive integers.
  If there are no primes, return 0.

  Examples:
  >>> (largest-prime-digit-sum [0 3 nil 2 1 3 5 7 4 5 5 5 2 181 32 4 32 3 2 32 324 4 3])
  10
  >>> (largest-prime-digit-sum (take 20 (concat [1 0 1 8 2 4597] (repeat nil))))
  25
  >>> (largest-prime-digit-sum [1 3 1 32 5107 34 83278 109 163 23 2323 32 30 1 9 3])
  13
  >>> (largest-prime-digit-sum [0 724 32 71 99 32 6 0 5 91 83 0 5 6 nil])
  11
  >>> (largest-prime-digit-sum [0 81 12 3 1 21])
  3
  >>> (largest-prime-digit-sum (take 6 (iterate inc 0)))
  7"
  [lst]
  (let [prime? (fn [n]
                 (and (> n 1)
                      (not-any? #(zero? (rem n %))
                                (range 2 (inc (Math/sqrt n))))))
        digit-sum (fn [n]
                    (reduce + (map #(Character/digit ^char % 10) (str n))))
        primes (filter prime? (remove nil? lst))]
    (if-let [m (seq primes)]
      (digit-sum (apply max m))
      0)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 10 (largest-prime-digit-sum [0 3 nil 2 1 3 5 7 4 5 5 5 2 181 32 4 32 3 2 32 324 4 3]))))

(run-test test-variation)
