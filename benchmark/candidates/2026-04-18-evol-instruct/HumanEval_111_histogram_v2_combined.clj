(defn histogram
  "Given a collection of integers, return a map of the values that occur most often
  and their corresponding counts.
  If several values have the same highest frequency, return all of them.
  Ignore nil values.
  Examples:
  >>> (histogram [1 2 2 3 3 3])
  {3 3}
  >>> (histogram [1 1 2 2 3])
  {1 2, 2 2}
  >>> (histogram [nil 4 4 nil 5])
  {4 2}
  >>> (histogram [])
  {}"
  [coll]
  (let [freqs (frequencies (remove nil? coll))]
    (if (empty? freqs)
      {}
      (let [m (apply max (vals freqs))]
        (into {} (filter (fn [[_ v]] (= v m)) freqs)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {3 3} (histogram [1 2 2 3 3 3])))
  (is (= {1 2, 2 2} (histogram [1 1 2 2 3])))
  (is (= {4 2} (histogram [nil 4 4 nil 5])))
  (is (= {} (histogram []))))

(run-test test-variation)
