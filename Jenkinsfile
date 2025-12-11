pipeline{
	
	agent any
	
	tools{
	  maven 'Maven'
	}	

	stages{
		stage("Build"){
			steps{
				git 'https://github.com/jglick/simple-maven-project-with-tests.git'
				sh "mvn -Dmaven.test.failure.ignore=true clean package"
			}
			post
			{
				success
					{
						junit '**/target/surefire-reports/TEST-*.xml'
						archiveArtifacts 'target/*.jar'
					}
			}
		}
		
		stage("Deploy to dev"){
			steps{
				echo("deploy to Dev");
			}
		}

		stage("Run Sanity API Test on Dev"){
			steps{
				catchError(buildResult: 'SUCCESS',stageResult: 'FAILURE'){
					git 'https://github.com/prakritshrivastava/APIFramework.git'
					sh "mvn clean test -Denv=dev -DsuiteXmlFiles=src/test/resources/testrunners/testng_regression.xml"
				}
			}
		}
		
		stage("Deploy to QA"){
			steps{
				echo("Deploy to QA");
			}
		}
		
		stage("Run Regression API Test on QA"){
			steps{
				catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE'){
					git 'https://github.com/prakritshrivastava/APIFramework.git'
					sh "mvn clean test -Denv=qa -DsuiteXmlFiles=src/test/resources/testrunners/testng_regression.xml"
				}
			}
		}
		
		stage("Deploy to PROD"){
			steps{
				echo("Deploy to PROD");
			}
		}
		
		stage("Run Regression API Test on PROD"){
			steps{
				catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE'){
					git 'https://github.com/prakritshrivastava/APIFramework.git'
					sh "mvn clean test -Denv="qa" -DsuiteXmlFiles=src/test/resources/testrunners/testng_regression.xml"
				}
			}
		}
		
		stage("Publish Chaintest Report"){
			steps{
					publishHTML([allowMissing:false,
								 alwaysLinkoLastBuid: false,
								 keepAll: true,
								 reportDir: 'target/chaintest',
								 reportFile: 'Index.html',
								 reportName: 'HTML API Regression Chaintest Report',
								 reportTitles: ''	
								])
			
			}
		}
		
	}
}