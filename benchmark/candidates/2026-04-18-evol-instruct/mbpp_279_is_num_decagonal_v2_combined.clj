(defn decagonal-index
  "Return the 1-based index of a decagonal number n, or nil if n is not decagonal.
  Examples:
  (decagonal-index 1)   ;=> 1
  (decagonal-index 17)  ;=> 2
  (decagonal-index 32)  ;=> 3
  (decagonal-index 7)   ;=> nil"
  [n]
  (let [disc (+ 9 (* 24 n))
        root (Math/sqrt disc)
        k (/ (+ 3 root) 6)]
    (when (and (integer? k)
               (>= n 1)
               (= n (long (/ (* k (- (* 5 k) 3)) 2))))
      (long k))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 1 (decagonal-index 1)))
  (is (= 2 (decagonal-index 17)))
  (is (= 3 (decagonal-index 32)))
  (is (= nil (decagonal-index 7))))

(run-test test-variation)
