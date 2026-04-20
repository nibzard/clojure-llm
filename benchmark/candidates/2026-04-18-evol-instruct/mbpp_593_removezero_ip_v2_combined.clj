(defn normalize-ip
  "Normalize an IPv4 address string by removing leading zeroes from each octet.
  Returns nil for invalid input.

  Examples:
  (normalize-ip \"192.168.001.010\") => \"192.168.1.10\"
  (normalize-ip \"010.000.000.001\") => \"10.0.0.1\"
  (normalize-ip nil) => nil
  (normalize-ip \"256.1.2.3\") => nil"
  [ip]
  (when (string? ip)
    (let [parts (clojure.string/split ip #"\.")]
      (when (= 4 parts)
        (let [nums (map #(try
                           (Integer/parseInt %)
                           (catch Exception _ nil))
                        parts)]
          (when (every? some? nums)
            (when (every? #(<= 0 % 255) nums)
              (clojure.string/join "." nums))))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "192.168.1.10" (normalize-ip "192.168.001.010")))
  (is (= "10.0.0.1" (normalize-ip "010.000.000.001")))
  (is (= nil (normalize-ip nil))))

(run-test test-variation)
