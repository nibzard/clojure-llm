(defn modp
  "Return 2^n modulo p for n given as a potentially large string.
  Use only the decimal digits of n; ignore any leading/trailing whitespace.
  Return nil if n is not a valid non-negative integer string or if p is not positive.

  >>> (modp \"3\" 5)
  3
  >>> (modp \" 1101 \" 101)
  2
  >>> (modp \"0\" 101)
  1
  >>> (modp \"100\" 101)
  1
  >>> (modp \"12a\" 7)
  nil"
  [n p]
  (when (and (string? n) (pos? p))
    (let [s (clojure.string/trim n)]
      (when (re-matches #"\d+" s)
        (let [exp (bigint s)]
          (loop [e exp
                 base 2N
                 acc 1N]
            (if (zero? e)
              (mod acc p)
              (recur (quot e 2)
                     (mod (* base base) p)
                     (if (odd? e) (mod (* acc base) p) acc)))))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 3 (modp "3" 5)))
  (is (= 2 (modp " 1101 " 101)))
  (is (= 1 (modp "0" 101)))
  (is (= 1 (modp "100" 101)))
  (is (= nil (modp "12a" 7))))

(run-test test-variation)
