(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= ["apple" "apple" "banana"]
         (normalize-keywords [:Banana :apple :Apple])))
  (is (= []
         (normalize-keywords nil))))

(run-test test-variation)
