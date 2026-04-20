(defn median_of_many
  "Return the median of a collection of numbers.

  If the collection has odd length, return the middle value after sorting.
  If it has even length, return the average of the two middle values.

  Examples:
  (median_of_many [3 1 2])    ;=> 2
  (median_of_many [1 2 3 4])  ;=> 2.5
  (median_of_many nil)       ;=> nil
  (median_of_many [])        ;=> nil"
  [nums]
  (when-let [s (seq nums)]
    (let [v (sort s)
          n (count v)
          mid (quot n 2)]
      (if (odd? n)
        (nth v mid)
        (/ (+ (nth v (dec mid))
              (nth v mid))
           2.0)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (median_of_many [3 1 2])))
  (is (= 2.5 (median_of_many [1 2 3 4])))
  (is (= nil (median_of_many nil)))
  (is (= nil (median_of_many []))))

(run-test test-variation)
