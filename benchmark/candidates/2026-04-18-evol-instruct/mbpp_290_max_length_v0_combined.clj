(defn longest-subseq
  "Return the longest subsequence of the given collection.

  If multiple subsequences share the maximum length, return the first one.
  Return nil for an empty collection.

  Examples:
  (longest-subseq [[:a] [:b :c] [:d]])
  ;; => [:b :c]

  (longest-subseq [])
  ;; => nil"
  [coll]
  (reduce
    (fn [best x]
      (if (> (count x) (count best))
        x
        best))
    nil
    coll))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [:b :c] (longest-subseq [[:a] [:b :c] [:d]])))
  (is (= [1 2 3 4] (longest-subseq [[1 2] [1 2 3 4] [5]])))
  (is (= nil (longest-subseq []))))

(run-test test-variation)
