(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '("zebra" "fizz" "buzz") (text_match_wordz "zebra fizz buzz"))))

(run-test test-variation)
