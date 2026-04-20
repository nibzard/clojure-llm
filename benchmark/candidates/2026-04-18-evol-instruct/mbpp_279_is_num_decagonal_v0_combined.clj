(defn decagonal-index
  "Return the 1-based position of a decagonal number n in the decagonal sequence, or nil if n is not decagonal.
  Examples:
  (decagonal-index 1) => 1
  (decagonal-index 8) => 2
  (decagonal-index 25) => 3
  (decagonal-index 26) => nil"
  [n]
  (let [disc (+ 1 (* 12 n))
        s (long (Math/sqrt disc))]
    (when (= (* s s) disc)
      (let [k (/ (+ 3 s) 6)]
        (when (and (integer? k) (pos? k))
          k)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 1 (decagonal-index 1)))
  (is (= 2 (decagonal-index 8)))
  (is (= 3 (decagonal-index 25)))
  (is (= nil (decagonal-index 26))))

(run-test test-variation)
