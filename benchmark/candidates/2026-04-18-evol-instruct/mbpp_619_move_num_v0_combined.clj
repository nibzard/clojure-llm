(defn move-num
  "Return a vector with all non-nil values first and all nil values moved to the end.

  Examples:
  (move-num [1 nil 2 nil 3]) => [1 2 3 nil nil]
  (move-num nil) => []
  (move-num []) => []"
  [xs]
  (let [xs (or xs [])]
    (vec (concat (remove nil? xs)
                 (filter nil? xs)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 2 3 nil nil] (move-num [1 nil 2 nil 3])))
  (is (= [] (move-num nil)))
  (is (= [] (move-num []))))

(run-test test-variation)
