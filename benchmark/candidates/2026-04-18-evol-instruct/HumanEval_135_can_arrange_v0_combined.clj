(defn first_nonincreasing-index
  "Return the first index in the vector where an element is less than or equal to
  the element immediately before it. If no such position exists, return -1.

  Examples:
  (first_nonincreasing-index [1 2 4 3 5])
  => 3

  (first_nonincreasing-index [1 2 3])
  => -1

  (first_nonincreasing-index [10])
  => -1"
  [v]
  (or (first
       (keep-indexed
        (fn [i x]
          (when (and (pos? i)
                     (<= x (nth v (dec i))))
            i))
        v))
      -1))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 3 (first_nonincreasing-index [1 2 4 3 5])))
  (is (= -1 (first_nonincreasing-index [1 2 3])))
  (is (= -1 (first_nonincreasing-index [10]))))

(run-test test-variation)
