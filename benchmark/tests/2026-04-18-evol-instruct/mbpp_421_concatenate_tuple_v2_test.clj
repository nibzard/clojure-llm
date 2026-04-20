(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "a/b/c" (join-keywords [:a :b :c] "/"))))

(run-test test-variation)
