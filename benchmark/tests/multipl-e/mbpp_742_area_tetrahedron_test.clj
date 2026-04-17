(require '[clojure.test :refer [deftest is run-test]])

(def candidate area_tetrahedron)

(deftest test-humaneval

  (is (= (candidate 3) 15.588457268119894))
  (is (= (candidate 20) 692.8203230275509))
  (is (= (candidate 10) 173.20508075688772))
)

(run-test test-humaneval)