(defn max-nested
  "Return the maximum numeric value found in a nested collection.
  
  Non-numeric values are ignored. Supports vectors, lists, sets, and lazy
  sequences. Returns nil if no numeric values are present.
  
  Examples:
  (max-nested [1 [4 nil] #{2 9} '(3 \"x\")]) => 9
  (max-nested [nil :a []]) => nil"
  [coll]
  (letfn [(nums [x]
            (lazy-seq
              (cond
                (number? x) (list x)
                (sequential? x) (mapcat nums x)
                (set? x) (mapcat nums x)
                :else '())))]
    (reduce max nil (nums coll))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 9 (max-nested [1 [4 nil] #{2 9} '(3 "x")])))
  (is (= nil (max-nested [nil :a []])))
  (is (= 42 (max-nested (lazy-seq [1 [2] #{42} "ignore"]))))

(run-test test-variation)
