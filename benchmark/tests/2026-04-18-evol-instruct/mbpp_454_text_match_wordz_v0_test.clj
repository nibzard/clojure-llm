(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= ["zebra" "pizza" "buzz"]
         (text_match_wordz ["zebra" "apple" "pizza" "buzz" "cat"]))))

(run-test test-variation)
