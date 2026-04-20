(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [:a 0] (find_keyword_position [:a 1 "x" :b])))
  (is (= [:b 2] (find_keyword_position [1 2 :b :c])))
  (is (= nil (find_keyword_position [1 "x" true])))
  (is (= [:k 1] (find_keyword_position '("a" :k 3)))))

(run-test test-variation)
