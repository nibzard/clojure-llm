(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [:ba 1 :C :erujolc] (solve [:ab 1 :C :clojure])))
  (is (= [3 2 1] (solve [1 2 3])))
  (is (= [:x nil true "ok"] (solve [:x nil true "ok"]))))

(run-test test-variation)
