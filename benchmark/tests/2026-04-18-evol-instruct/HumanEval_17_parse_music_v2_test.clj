(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [4 2 1] (parse_music "w h q")))
  (is (= [1 2 4 1] (parse_music "  q\n\th   w   q ")))
  (is (= [] (parse_music "")))
  (is (= [] (parse_music nil))))

(run-test test-variation)
