(defn sort-by-frequency
  "Return the elements of coll sorted by descending frequency.
  Break ties by the original order of first appearance.

  Examples:
  (sort-by-frequency [1 2 2 3 3 3]) ;=> [3 3 3 2 2 1]
  (sort-by-frequency [:a :b :a :c :b]) ;=> [:a :a :b :b :c]
  (sort-by-frequency nil) ;=> nil"
  [coll]
  (when coll
    (let [freqs (frequencies coll)
          first-idx (zipmap (distinct coll) (range))]
      (sort-by (fn [x] [(- (freqs x)) (first-idx x)]) coll))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [3 3 3 2 2 1] (sort-by-frequency [1 2 2 3 3 3])))
  (is (= [:a :a :b :b :c] (sort-by-frequency [:a :b :a :c :b])))
  (is (= nil (sort-by-frequency nil))))

(run-test test-variation)
