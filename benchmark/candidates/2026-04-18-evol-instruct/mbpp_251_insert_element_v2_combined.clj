(defn insert_element
  "Given a vector and an element, return a new vector with the element inserted after every odd-indexed item (0-based).
  Preserve the original order and handle empty vectors.

  Examples:
  (insert_element [1 2 3 4] :x) => [1 :x 2 3 :x 4]
  (insert_element [] :x) => []"
  [v element]
  (vec
   (mapcat (fn [[idx x]]
             (if (odd? idx)
               [x element]
               [x]))
           (map-indexed vector v))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 :x 2 3 :x 4] (insert_element [1 2 3 4] :x)))
  (is (= [] (insert_element [] :x)))
  (is (= [:a :z :b] (insert_element [:a :b] :z))))

(run-test test-variation)
