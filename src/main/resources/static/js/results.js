    let letterTest = document.getElementById("testy").innerHTML;
    console.log(letterTest);
    console.log(referenceNumber);
    let datesArray = [];
    let resultsObjectsArray = [];
    class ResultsObject {
      constructor(date, totalCorrect, totalIncorrect, name) {  // Constructor
        this.date = date;
        this.totalCorrect = totalCorrect;
        this.totalIncorrect = totalIncorrect;
        this.name = name;
      }
    }
    function passDataByLetter(arr,innerTest){
        if (innerTest == null){
            for (let entry of arr){
                 if(entry.userid == referenceNumber)  {
                    let totalDate = (entry.month) + "/" + (entry.day) + "/" + (entry.year);

                    if (!datesArray.includes(totalDate)){
                        datesArray.push(totalDate);
                        if (entry.correct == "yes"){
                            resultsObjectsArray.push(new ResultsObject(totalDate, 1, 0, entry.name));
                        } else {
                            resultsObjectsArray.push(new ResultsObject(totalDate, 0, 1, entry.name));
                        }

                    } else {
                        for (let object of resultsObjectsArray){
                            if (object.date == totalDate){
                                if (entry.correct == "yes"){
                                    object.totalCorrect ++;
                                } else {
                                    object.totalIncorrect ++;
                                }
                            }
                        }

                    }
                 }
             }
         }
                 else {
                     for (let entry of arr){
                         if(entry.userid == referenceNumber) {
                               if (innerTest == entry.name){
                                 let totalDate = (entry.month) + "/" + (entry.day) + "/" + (entry.year);

                                 if (!datesArray.includes(totalDate)){
                                     datesArray.push(totalDate);
                                     if (entry.correct == "yes"){
                                         resultsObjectsArray.push(new ResultsObject(totalDate, 1, 0, entry.name));
                                     } else {
                                         resultsObjectsArray.push(new ResultsObject(totalDate, 0, 1, entry.name));
                                     }

                                 } else {
                                     for (let object of resultsObjectsArray){
                                         if (object.date == totalDate){
                                             if (entry.correct == "yes"){
                                                 object.totalCorrect ++;
                                             } else {
                                                 object.totalIncorrect ++;
                                             }
                                         }
                                     }

                                 }
                            }
                         }
                     }
                  }
    }
    let letterArray = [""]
    if (letterTest == "allWords"){
        passDataByLetter(myArray);
    }
    if (letterTest == "starts_with_b"){
             passDataByLetter(myArray, "Words Starting With B");
    }
    if (letterTest == "ends_with_b"){
             passDataByLetter(myArray, "Words Ending In B");
    }
    if (letterTest == "starts_with_f"){
             passDataByLetter(myArray, "Words Starting With F");
    }
    if (letterTest == "ends_with_f"){
             passDataByLetter(myArray, "Words Ending In F");
    }
    if (letterTest == "starts_with_h"){
             passDataByLetter(myArray, "Words Starting With H");
    }
    if (letterTest == "starts_with_m"){
             passDataByLetter(myArray, "Words Starting With M");
    }
    if (letterTest == "ends_with_m"){
             passDataByLetter(myArray, "Words Ending In M");
    }
    if (letterTest == "starts_with_n"){
             passDataByLetter(myArray, "Words Starting With N");
    }
    if (letterTest == "ends_with_n"){
             passDataByLetter(myArray, "Words Ending In N");
    }
    if (letterTest == "starts_with_p"){
             passDataByLetter(myArray, "Words Starting With P");
    }
    if (letterTest == "ends_with_p"){
             passDataByLetter(myArray, "Words Ending In P");
    }
    if (letterTest == "starts_with_w"){
             passDataByLetter(myArray, "Words Starting With W");
    }



    const dataset = [];
    const dateSet = [];

    for (object of resultsObjectsArray){
        let obj = {};
        obj.percent = (Math.round(object.totalCorrect/(object.totalCorrect + object.totalIncorrect)*100));
        obj.date = object.date;
        dataset.push(obj);
    }
    console.log(resultsObjectsArray);

    const w = 400;
    const h = (dataset.length + 1) * 40;

    const svg = d3.select("body")
                  .append("svg")
                  .attr("class", "myD3rectangle")
                  .attr("width", w)
                  .attr("height", h + 20);

    svg.selectAll("rect")
       .data(dataset)
       .enter()
       .append("rect")
       .attr("y", (d, i) => (i * h/(dataset.length)) + (h/(dataset.length + 2))/2  )
       .attr("x", 0)
       .attr("height", h/(dataset.length + 2))
       .attr("width", (d, i) => 2.5 * d.percent)
       .attr("fill", "black");

    svg.selectAll("text")
       .data(dataset)
       .enter()
       .append("text")
       .text((d) => d.date + ": " + d.percent + "%")
       .attr("height", h/(dataset.length + 2))
       .attr("y", (d, i) => (i * h/(dataset.length)) + (h/(dataset.length + 2))/2  + (h/(dataset.length + 2))/2)
       .attr("x", (d, i) => 2.5 * d.percent + 3);


