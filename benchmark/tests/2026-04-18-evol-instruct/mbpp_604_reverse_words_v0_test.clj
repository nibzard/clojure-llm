(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= ["world" "hello"] (reverse_words ["hello" "world"]))))

(run-test test-variation)
