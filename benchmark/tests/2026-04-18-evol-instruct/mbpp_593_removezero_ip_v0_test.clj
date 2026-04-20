(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "192.168.1.10" (normalize-ip "192.168.001.010"))))

(run-test test-variation)
