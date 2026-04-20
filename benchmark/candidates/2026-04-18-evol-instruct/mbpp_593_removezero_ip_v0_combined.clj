(defn normalize-ip
  "Normalize an IP address by removing leading zeroes from each numeric octet.

  Examples:
  (normalize-ip \"192.168.001.010\") => \"192.168.1.10\"
  (normalize-ip \"000.000.000.000\") => \"0.0.0.0\"
  (normalize-ip nil) => nil"
  [ip]
  (when ip
    (clojure.string/join "."
                          (map #(Integer/parseInt %)
                               (clojure.string/split ip #"\.")))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "192.168.1.10" (normalize-ip "192.168.001.010"))))

(run-test test-variation)
