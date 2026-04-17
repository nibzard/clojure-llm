(require '[clojure.test :refer [deftest is run-test]])

(def candidate count_occurance)

(deftest test-humaneval

  (is (= (candidate "letstdlenstdporstd") 3))
  (is (= (candidate "truststdsolensporsd") 1))
  (is (= (candidate "makestdsostdworthit") 2))
  (is (= (candidate "stds") 1))
  (is (= (candidate "") 0))
)

(run-test test-humaneval)