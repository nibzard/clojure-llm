(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [4 2 1 2] (parse_music "x x| .| x|")))
  (is (= [] (parse_music nil))))

(run-test test-variation)
