(defn occurrence-indices
  "Return a lazy sequence of all starting indices where `pattern` appears in `coll`.
  Works on any sequential collection, not just strings.

  Examples:
  (occurrence-indices [1 2 3 1 2 4] [1 2])
  => (0 3)

  (occurrence-indices \"banana\" \"ana\")
  => (1 3)

  Return nil if there are no matches."
  [coll pattern]
  (let [c (seq coll)
        p (seq pattern)
        plen (count pattern)]
    (when (and c p (pos? plen))
      (letfn [(step [xs idx]
                (lazy-seq
                  (when-let [s (seq xs)]
                    (let [match? (= pattern (take plen s))
                          rest-seq (rest s)]
                      (if match?
                        (cons idx (step rest-seq (inc idx)))
                        (step rest-seq (inc idx)))))))]
        (let [result (step c 0)]
          (when (seq result) result)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '(0 3) (occurrence-indices [1 2 3 1 2 4] [1 2])))
  (is (= '(1 3) (occurrence-indices "banana" "ana")))
  (is (= nil (occurrence-indices [1 2 3] [4 5]))))

(run-test test-variation)
