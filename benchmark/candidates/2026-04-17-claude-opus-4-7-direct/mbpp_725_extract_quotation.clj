(defn extract_quotation
  "Write a function to extract values between quotation marks \" \" of the given string."
  [text1]
  (when (string? text1)
    (let [matches (re-seq #"(?<=\")[^\"]*(?=\")" text1)]
      (when matches
        (map first matches)))))