(defn equal-shape?
  "Return true if every collection in `colls` has the same count, otherwise false.

  Works with nil and empty collections.

  Examples:
  (equal-shape? [[1 2] [:a :b] '(x y)]) ;=> true
  (equal-shape? [[1] [2 3]])            ;=> false
  (equal-shape? [])                     ;=> true
  (equal-shape? [nil []])               ;=> true"
  [colls]
  (let [sizes (map #(count (or % [])) colls)]
    (apply = sizes)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (equal-shape? [[1 2] [:a :b] '(x y)])))
  (is (= false (equal-shape? [[1] [2 3]])))
  (is (= true (equal-shape? [])))
  (is (= true (equal-shape? [nil []]))))

(run-test test-variation)
