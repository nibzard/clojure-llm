(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (sorted-by-length? [nil "a" "bb"])))
  (is (= false (sorted-by-length? ["aa" "b"])))
  (is (= true (sorted-by-length? []))))

(run-test test-variation)
