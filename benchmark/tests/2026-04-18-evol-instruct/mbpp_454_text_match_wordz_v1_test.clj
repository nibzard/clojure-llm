(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "lazy" (text_match_wordz "lazy dog"))))

(run-test test-variation)
