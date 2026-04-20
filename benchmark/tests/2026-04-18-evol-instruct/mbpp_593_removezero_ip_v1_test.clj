(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "192.168.1.10" (normalize-ip "192.168.001.010")))
  (is (= "10.0.0.1" (normalize-ip "010.000.000.001")))
  (is (= nil (normalize-ip nil))))

(run-test test-variation)
