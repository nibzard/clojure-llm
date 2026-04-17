(require '[clojure.test :refer [deftest is run-test]])

(def candidate flip_case)

(deftest test-humaneval

  (is (= (candidate "") ""))
  (is (= (candidate "Hello!") "hELLO!"))
  (is (= (candidate "These violent delights have violent ends") "tHESE VIOLENT DELIGHTS HAVE VIOLENT ENDS"))
)

(run-test test-humaneval)