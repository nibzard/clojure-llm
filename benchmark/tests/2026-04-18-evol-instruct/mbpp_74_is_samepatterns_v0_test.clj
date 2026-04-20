(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (pattern-bijection? [:red :blue :red] [1 2 1])))
  (is (= false (pattern-bijection? [:red :blue :red] [1 1 1])))
  (is (= true (pattern-bijection? nil nil))))

(run-test test-variation)
