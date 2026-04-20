(defn problem-prime-numbers [a]
  (letfn [(prime? [n]
            (and (> n 1)
                 (not-any? #(zero? (mod n %))
                           (range 2 (inc (int (Math/sqrt n)))))))]
    (loop [n 2
           primes []]
      (if (= (count primes) a)
        primes
        (recur (inc n) (if (prime? n) (conj primes n) primes))))))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-prime-numbers
  (is (= (= (problem-prime-numbers 2) [2 3])))
  (is (= (= (problem-prime-numbers 5) [2 3 5 7 11])))
  (is (= (= (last (problem-prime-numbers 100)) 541))))

(run-tests)
