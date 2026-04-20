(defn sort-by-frequency
  "Return the elements of coll sorted by descending frequency, then by their natural order.
  
  Examples:
  (sort-by-frequency [3 1 2 2 3 3])
  ;; => [3 3 3 2 2 1]
  
  (sort-by-frequency nil)
  ;; => []
  
  (sort-by-frequency [])
  ;; => []"
  [coll]
  (let [coll (or coll [])
        freqs (frequencies coll)]
    (->> coll
         (sort-by (fn [x] [(- (freqs x)) x]))
         vec)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [3 3 3 2 2 1] (sort-by-frequency [3 1 2 2 3 3])))
  (is (= [] (sort-by-frequency nil)))
  (is (= [] (sort-by-frequency []))))

(run-test test-variation)
