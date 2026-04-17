(require '[clojure.test :refer [deftest is run-test]])

(def candidate check_expression)

(deftest test-humaneval

  (is (= (candidate "{()}[{}]") true))
  (is (= (candidate "{()}[{]") false))
  (is (= (candidate "{()}[{}][]({})") true))
)

(run-test test-humaneval)