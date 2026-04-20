(defn common-element?
  "Return true if two collections share at least one common element.

  Works with any seqable collections, including vectors, lists, sets, and lazy seqs.

  Examples:
  (common-element? [1 2 3] '(4 5 2)) ;=> true
  (common-element? nil [1 2 3])      ;=> false
  (common-element? (range) [1000])   ;=> true"
  [coll1 coll2]
  (boolean
   (and (seq coll1)
        (seq coll2)
        (some (set coll1) coll2))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (common-element? [1 2 3] '(4 5 2))))
  (is (= false (common-element? nil [1 2 3])))
  (is (= true (common-element? (range) [1000]))))

(run-test test-variation)
