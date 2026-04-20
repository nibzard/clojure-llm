(defn interleave-with-index
  "Return a vector of pairs where each element is paired with its zero-based index.

  Works with any sequential collection and preserves order.
  If the input is empty, returns an empty vector.

  >>> (interleave-with-index [])
  []
  >>> (interleave-with-index [:a :b :c])
  [[:a 0] [:b 1] [:c 2]]"
  [coll]
  (vec (map vector coll (range))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [] (interleave-with-index [])))
  (is (= [[:a 0] [:b 1] [:c 2]] (interleave-with-index [:a :b :c]))))

(run-test test-variation)
