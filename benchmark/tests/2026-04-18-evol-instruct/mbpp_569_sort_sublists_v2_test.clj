(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [["a" "bb" "ccc"] ["z" "yy"]]
         (sort_sublists_by-length [["bb" "a" "ccc"] ["z" "yy"]]))))

(run-test test-variation)
