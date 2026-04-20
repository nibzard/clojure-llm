(defn is_prime_in_range
  "Return true if every number in the inclusive range [start, end] is prime.
  Treat nil bounds as invalid and return false.
  >>> (is_prime_in_range 11 13)
  true
  >>> (is_prime_in_range 10 13)
  false
  >>> (is_prime_in_range 2 2)
  true
  >>> (is_prime_in_range nil 7)
  false
  >>> (is_prime_in_range 17 11)
  false"
  [start end]
  (letfn [(prime? [n]
            (and (integer? n)
                 (>= n 2)
                 (not-any? #(zero? (mod n %))
                           (range 2 (inc (Math/sqrt n))))))]
    (and (some? start)
         (some? end)
         (<= start end)
         (every? prime? (range start (inc end))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (is_prime_in_range 11 13)))
  (is (= false (is_prime_in_range 10 13)))
  (is (= true (is_prime_in_range 2 2)))
  (is (= false (is_prime_in_range nil 7)))
  (is (= false (is_prime_in_range 17 11))))

(run-test test-variation)
