(defn prime-factors
  "Return a vector of the prime factors of n in nondecreasing order.
  If n is less than 2, return an empty vector.
  >>> (prime-factors 1)
  []
  >>> (prime-factors 2)
  [2]
  >>> (prime-factors 12)
  [2 2 3]
  >>> (prime-factors 97)
  [97]
  >>> (prime-factors 360)
  [2 2 2 3 3 5]"
  [n]
  (loop [n n
         d 2
         factors []]
    (cond
      (< n 2) []
      (= n 1) factors
      (> (* d d) n) (conj factors n)
      (zero? (mod n d)) (recur (/ n d) d (conj factors d))
      :else (recur n (inc d) factors))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [] (prime-factors 1)))
  (is (= [2] (prime-factors 2)))
  (is (= [2 2 3] (prime-factors 12)))
  (is (= [97] (prime-factors 97))))

(run-test test-variation)
