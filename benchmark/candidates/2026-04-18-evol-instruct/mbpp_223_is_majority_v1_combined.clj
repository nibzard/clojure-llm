(defn majority-index
  "Given a sorted vector of integers, return the zero-based index of the first occurrence of the majority element, or nil if no majority element exists.

  The majority element is the value that appears more than half the time in the vector.

  Examples:
  (majority-index [1 1 1 2 3])   ;; => 0
  (majority-index [1 2 2 2 3])   ;; => 1
  (majority-index [1 2 3 4])     ;; => nil
  (majority-index [])            ;; => nil"
  [v]
  (when-let [x (nth v (quot (count v) 2) nil)]
    (let [c (count (filter #(= % x) v))]
      (when (> c (/ (count v) 2))
        (.indexOf v x)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 0 (majority-index [1 1 1 2 3])))
  (is (= 1 (majority-index [1 2 2 2 3])))
  (is (= nil (majority-index [1 2 3 4])))
  (is (= nil (majority-index []))))

(run-test test-variation)
